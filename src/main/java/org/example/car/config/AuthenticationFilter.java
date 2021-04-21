package org.example.car.config;



import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.car.model.security.JwtUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by lovro.vrlec on Apr,2021
 *
 * AuthenticationFilter for authenticating with API gateway.
 */

@Slf4j
class AuthenticationFilter extends OncePerRequestFilter {

  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * internal filter.
   *
   * @param httpServletRequest  httpServletRequest
   * @param httpServletResponse httpServletResponse
   * @param filterChain         filterChain
   * @throws ServletException ServletException
   * @throws IOException      IOException
   */
  @Override
  protected void doFilterInternal(final HttpServletRequest httpServletRequest,
                                  final HttpServletResponse httpServletResponse, final FilterChain filterChain)
          throws IOException, ServletException {
    authenticationByJwt(httpServletRequest, httpServletResponse, filterChain);
  }

  /**
   * Authentication by Jwt.
   *
   * @param httpServletRequest  httpServletRequest
   * @param httpServletResponse httpServletResponse
   * @param filterChain         filterChain
   * @throws IOException IOException
   */
  private void authenticationByJwt(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse, FilterChain filterChain)
          throws IOException, ServletException {
    String jwt = httpServletRequest.getHeader("x-consumer-identity");
    if (jwt == null) {

        log.debug("x-consumer-identity is null. Authorization header missing.");
        httpServletResponse
                .sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Authorization header is missing.");
        return;
    }

    String decodedString = decodeJwtFromBase64(jwt);
    JwtUser jwtUser = null;

    try {
      jwtUser = mapper.readValue(decodedString, JwtUser.class);
    } catch (JsonParseException | JsonMappingException e) {
      log.debug("Exception during parsing json ", e);
      httpServletResponse
              .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is not correct.");
      return;
    }

    jwtUser.setEncodedJwt(jwt);
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtUser,
            null);
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  /**
   *
   * @param jwt base64 jwt
   * @return String
   */
  private String decodeJwtFromBase64(String jwt){
    byte[] decodedBytes = Base64.getDecoder().decode(jwt);
    return new String(decodedBytes);
  }
}
