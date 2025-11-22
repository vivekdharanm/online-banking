
public class AuthController 
{
		  private final UserService userService;
		  private final AccountService accountService;
		  private final JwtUtil jwtUtil;

		  public AuthController(UserService u, AccountService a, JwtUtil j)
		  {	
			  	this.userService=u;
			  	this.accountService=a;this.jwtUtil=j;
		  }

		  @PostMapping("/register")
		  public ResponseEntity<?> register(@RequestBody RegisterRequest req) 
		  {
			  User u = userService.register(req.name, req.email, req.password);
			  Account a = accountService.createAccountForUser(u);
			  String token = jwtUtil.generateToken(u.getEmail(), u.getRole());
			  return ResponseEntity.ok(new AuthResponse(token, u.getRole()));
		  }

		  @PostMapping("/login")
		  public ResponseEntity<?> login(@RequestBody AuthRequest req) 
		  {
			  	var opt = userService.findByEmail(req.email);
			  	if(opt.isEmpty()) return ResponseEntity.badRequest().body("Invalid credentials");
			  	User u = opt.get();
			  	// check password manually; but ideally use AuthenticationManager. For brevity:
			  	if(!new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(req.password, u.getPassword())) 
			  	{
			  			return ResponseEntity.badRequest().body("Invalid credentials");
			  	}
			  	String token = jwtUtil.generateToken(u.getEmail(), u.getRole());
			  	return ResponseEntity.ok(new AuthResponse(token, u.getRole()));
		  }

}
