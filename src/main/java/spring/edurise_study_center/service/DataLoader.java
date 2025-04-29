package spring.edurise_study_center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.entity.Admin;
import spring.edurise_study_center.repository.AdminRepository;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AdminRepository adminRepository;
    @Value("${spring.sql.init.mode}")
    private String initMode;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFirstName("Ilyosbek");
            admin.setLastName("Ilyosbek");
            admin.setNumber("+998900245052");

            adminRepository.save(admin);


        }


    }
}
