    package com.example.recommendershop.service.user;

    import com.example.recommendershop.dto.ResponseData;
    import com.example.recommendershop.dto.user.request.LoginRequest;
    import com.example.recommendershop.dto.user.request.UserRequest;
    import com.example.recommendershop.dto.user.response.UserInfor;
    import com.example.recommendershop.entity.User;
    import com.example.recommendershop.enums.Role;
    import com.example.recommendershop.exception.MasterException;
    import com.example.recommendershop.mapper.UserMapper;
    import com.example.recommendershop.repository.UserRepository;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;

    import java.util.Optional;
    import java.util.UUID;

    @Service
    public class UserServiceImpl implements UserService {
        @Autowired
        private final UserRepository userRepository;
        private final HttpSession httpSession;
        private final UserMapper userMapper;


        public UserServiceImpl(UserRepository userRepository, HttpSession httpSession, UserMapper userMapper) {
            this.userRepository = userRepository;
            this.httpSession = httpSession;
            this.userMapper = userMapper;
        }

        @Override
        public ResponseData<?> register(UserRequest userRequest) {
            if (userRepository.getUserByName(userRequest.getName()) != null) {
                throw new MasterException(HttpStatus.BAD_REQUEST, "Ten tai khoan da ton tai");
            }
            if (userRepository.getUserByEmail(userRequest.getEmail()) != null) {
                throw new MasterException(HttpStatus.BAD_REQUEST, "Email da duoc su dung");
            }
            User user = userMapper.toEntity(userRequest);
            user.setRole(Role.USER);
            userRepository.save(user);
            return new ResponseData<>(HttpStatus.OK.value(), "Tao tai khoan thanh cong");
        }

        @Override
        public ResponseData<?> login(LoginRequest loginRequest) {
            if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
                throw new MasterException(HttpStatus.BAD_REQUEST, "Email và mật khẩu không được để trống");
            }

            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            if (userOptional.isEmpty() || !loginRequest.getPassword().equals(userOptional.get().getPassword())) {
                throw new MasterException(HttpStatus.UNAUTHORIZED, "Email hoặc mật khẩu sai");
            }
            User user = userOptional.get();
            UUID token = user.getUserId();
            httpSession.setAttribute("UserId", user.getUserId());
            httpSession.setAttribute("UserName", user.getName());
            httpSession.setAttribute("Role", user.getRole().name());
            httpSession.setAttribute("AuthToken", token);
            return new ResponseData<>(HttpStatus.OK.value(), "Đăng nhập thành công", token);

        }
        @Override
        public void logout() {
            httpSession.invalidate();
        }
//        private String generateRandomPassword() {
//            String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//            String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
//            String numbers = "0123456789";
//            String specialChars = "!@#$%&_/?";
//            String allChars = upperCaseLetters + lowerCaseLetters + numbers + specialChars;
//            Random random = new Random();
//            StringBuilder password = new StringBuilder();
//            for (int i = 0; i < 10; i++) {
//                password.append(allChars.charAt(random.nextInt(allChars.length())));
//            }
//            return password.toString();
//        }
//        public void sendEmail(EmailMessage emailMessage){
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(emailMessage.getTo());
//            mailMessage.setSubject(emailMessage.getSubject());
//            mailMessage.setText(emailMessage.getBody());
//            javaMailSender.send(mailMessage);
//        }
//        @Override
//        public void forgotPasword(String email) {
//            Optional<User> userOptional = userRepository.findByEmail(email);
//                if(userOptional.isPresent()){
//                    User user = userOptional.get();
//                    String newPassword = generateRandomPassword();
//
//                    //save
//                    user.setPassword(passwordEncoder.encode(newPassword));
//                    userRepository.save(user);
//
//                    // send email
//                    EmailMessage emailMessage = new EmailMessage();
//                    emailMessage.setTo(email);
//                    emailMessage.setSubject("Reset password");
//                    emailMessage.setBody("Your new password: " + newPassword);
//                    sendEmail(emailMessage);
//                } else {
//                    throw new MasterException(HttpStatus.NOT_FOUND, "Email không tồn tại");
//                }
//
//                }

        public UserInfor detail(UUID userId){
            // Lấy UserId từ session
            String sessionUserId = (String) httpSession.getAttribute("UserId");

            // Kiểm tra xem session có tồn tại hay không
            if (sessionUserId == null) {
                throw new MasterException(HttpStatus.UNAUTHORIZED, "Chưa đăng nhập");
            }

            // Kiểm tra xem userId trong session có khớp với userId truyền vào không
            if (!sessionUserId.equals(userId.toString())) {
                throw new MasterException(HttpStatus.FORBIDDEN, "Không có quyền truy cập thông tin người dùng này");
            }

            // Nếu session hợp lệ, tiếp tục lấy thông tin người dùng và trả về
            return userMapper.toDao(userRepository.getReferenceById(userId));
        }

        public UserInfor update(UUID userId, UserRequest userRequest){
            User existingUser = userRepository.findById(userId).orElseThrow(()-> new MasterException(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng"));
            userMapper.update(userRequest, existingUser);
            User updatedUser = userRepository.save(existingUser);
            httpSession.setAttribute("UserId", updatedUser.getUserId().toString());
            httpSession.setAttribute("UserName", updatedUser.getName());
            httpSession.setAttribute("Role", existingUser.getRole().name());
            return userMapper.toDao(updatedUser);
        }


    }