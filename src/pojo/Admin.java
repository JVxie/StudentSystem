package pojo;

import lombok.Data;

@Data
public class Admin {
    private String adminId;

    private String adminPsw;

    public Admin(String adminId, String adminPsw) {
        this.adminId = adminId;
        this.adminPsw = adminPsw;
    }
}
