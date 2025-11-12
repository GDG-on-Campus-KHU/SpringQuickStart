package User.service;

import User.domain.User;

import java.util.List;

public interface UserService {
    // 회원 등록 (비번 해시 포함)
    User register(User user);

    // 수정
    User update(User user);

    // 삭제 (PK 기준)
    void delete(int id);

    // 단건 조회 (PK)
    User get(int id);

    // 사용자명으로 조회
    User getByUsername(String username);

    // 목록
    List<User> list();
}
