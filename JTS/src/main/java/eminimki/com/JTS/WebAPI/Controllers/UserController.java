package eminimki.com.JTS.WebAPI.Controllers;
/*Bu class security için çalışmaktadır. employee nesne ile işlemler yapar.*/
import eminimki.com.JTS.Business.Manager.EmployeeManager;
import eminimki.com.JTS.Security.AuthRequest;
import eminimki.com.JTS.Business.requests.EmployeeRequests.CreateEmployeeRequest;
import eminimki.com.JTS.Security.Jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final EmployeeManager employeeManager;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        throw new UsernameNotFoundException("invalid username {} " + request.username());
    }

}