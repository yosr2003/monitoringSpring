package com.example.ommpproject.projectommp.servicesimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ommpproject.projectommp.models.PasswordResetToken;
import com.example.ommpproject.projectommp.models.User;
import com.example.ommpproject.projectommp.repositories.PasswordResetTokenRepository;
import com.example.ommpproject.projectommp.repositories.UserRepositiry;
import com.example.ommpproject.projectommp.services.EmailService;
import com.example.ommpproject.projectommp.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepositiry UserRepository;

	
	
	@Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendPasswordResetEmail(String email) {
        User user = UserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24); // Token expires in 24 hours
        PasswordResetToken resetToken = new PasswordResetToken(token, user, expirationTime);

        passwordResetTokenRepository.save(resetToken); // Ensure expirationTime is set in PasswordResetToken entity

        String resetUrl = "http://localhost:4200/reset-password?token=" + token;
        emailService.sendEmail(user.getEmail(), "Password Reset Request",
                "Reset your password using the following link: " + resetUrl);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired token");
        }

        User user = resetToken.getUser();
        user.setPassword(newPassword);
        UserRepository.save(user);

        // Clean up token after reset
        passwordResetTokenRepository.delete(resetToken);
    }
	
	
	
	
	
	
	
//	@Override
//	public User addUser(User p) {
//		// TODO Auto-generated method stub
//		return UserRepository.save(p);
//	}

	@Override
	public User editUser(User p) {
		// TODO Auto-generated method stub
		return UserRepository.save(p);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		 Optional<User> user=UserRepository.findById(id);
		 return user.isPresent() ? user.get(): null;
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		UserRepository.deleteById(id);
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return UserRepository.findAll();
	}
	
//	@Override
//	public List<User> getListByport(Long id) {
//		// TODO Auto-generated method stub
//		return UserRepository.findList(id);
//	}



}
