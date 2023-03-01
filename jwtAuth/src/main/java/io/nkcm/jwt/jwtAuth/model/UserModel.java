package io.nkcm.jwt.jwtAuth.model;

public class UserModel extends JwtRequest{

    public UserModel(String username, String password) {
        super(username, password);
    }

    public UserModel() {
        super();
    }

}
