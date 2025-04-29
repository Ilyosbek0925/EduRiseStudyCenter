package spring.edurise_study_center.conf;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.edurise_study_center.entity.StudentBooks;
import spring.edurise_study_center.service.auth.AuthService;
import spring.edurise_study_center.service.jwt.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    final private String BOOK_API = "api/book";
    final private String STUDENT_API = "api/student";
    final private String PAYMENT_API = "api/payment";
    final private String TEACHER_API = "api/teacher";
    final private String ATTENDANCE_API = "api/attendance";
    final private String GROUP_API = "/api/group";

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService, JwtFilter jwtFilter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth ->
                        auth.requestMatchers(STUDENT_API + "/addStudent").permitAll()
                                .requestMatchers(STUDENT_API + "/getStudentsByGroupId/").hasRole("STUDENT")
                                .requestMatchers(STUDENT_API + "/findStudentById/").hasRole("STUDENT")
                                .requestMatchers(STUDENT_API+"/modifyStudent/").hasRole("STUDENT")
                                .requestMatchers(STUDENT_API + "/delete/").hasAnyRole("TEACHER", "STUDENT")
                                .requestMatchers(PAYMENT_API + "/markPayment").hasRole("ADMIN")
                                .requestMatchers(PAYMENT_API + "/update/").hasRole("ADMIN")
                                .requestMatchers(PAYMENT_API + "/delete/").hasRole("ADMIN")
                                .requestMatchers(PAYMENT_API + "/findPaymentsByGroupId/").hasRole("ADMIN")
                                .requestMatchers(PAYMENT_API + "/getAll").hasRole("ADMIN")
                                .requestMatchers(ATTENDANCE_API + "/getAll").hasAnyRole("ADMIN", "TEACHER")
                                .requestMatchers(ATTENDANCE_API + "/getAttendancesByGroupId/").hasRole("TEACHER")
                                .requestMatchers(ATTENDANCE_API + "/modify/").hasRole("TEACHER")
                                .requestMatchers(ATTENDANCE_API + "/deleteAttendance/").hasRole("TEACHER")
                                .requestMatchers(ATTENDANCE_API + "/markAttendance/").hasRole("TEACHER")
                                .requestMatchers(BOOK_API + "/downloadBook/").permitAll()
                                .requestMatchers(BOOK_API + "/getFileByBookId/").hasAnyRole("TEACHER", "STUDENT")
                                .requestMatchers(BOOK_API + "/getFilesByGroupId/").hasAnyRole("TEACHER", "STUDENT")
                                .requestMatchers(BOOK_API + "/uploadStudentBook").hasRole("TEACHER")
                                .requestMatchers(GROUP_API + "/addGroup").hasAnyRole("TEACHER", "ADMIN")
                                .requestMatchers(GROUP_API + "/deleteGroup").hasAnyRole("TEACHER", "ADMIN")
                                .requestMatchers(GROUP_API + "/getAll").hasRole("ADMIN")
                                .requestMatchers(GROUP_API + "/modifyGroup/").hasAnyRole("TEACHER", "ADMIN")
                                .requestMatchers(TEACHER_API + "/getAll").hasRole("ADMIN")
                                .requestMatchers(TEACHER_API + "/modifyTeacher/").hasRole("ADMIN")
                                .requestMatchers(TEACHER_API + "/deleteTeacher/").hasRole("ADMIN")
                                .requestMatchers(TEACHER_API + "/addTeacher").hasRole("ADMIN")
                                .requestMatchers(TEACHER_API + "/findById/").hasAnyRole("ADMIN", "TEACHER")
                                .anyRequest().permitAll())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
