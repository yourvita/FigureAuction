package figureauction.figureauction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter @Setter
public class Member {
    private long userId;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userAddr;
    private char userGrade;
    private LocalDateTime joinDate;
    private boolean userStatus;

    public Member(String userEmail, String userPw) {
        this.userEmail = userEmail;
        this.userPw = userPw;
    }
}
