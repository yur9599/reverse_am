//package com.example.reverse_am.configuration;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class TokenFilter extends OncePerRequestFilter {
//
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String authorization = request.getHeader("Authorization");
//        if (authorization != null){
//            try {
//                String token = authorization.substring(7);
//                JwtParser parser = Jwts.parser().setSigningKey("hola-kepasa");
//                parser.parse(token);
//                Claims claims = parser.parseClaimsJws(token).getBody();
//                String email = (String) claims.get("email");
//
//                AppUser appUser = new AppUser();
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(appUser,null,appUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
//}
