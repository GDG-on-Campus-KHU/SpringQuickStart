package User.service;

import User.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import User.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User register(User user) {
        // ✅ 비번은 반드시 해시해서 저장 (예: BCrypt)
        // 예시) user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 여기서는 일단 그대로 두고 주석으로 명시
        if (repo.existsByUserId(user.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다: " + user.getUserId());
        }
        return repo.save(user);
    }

    @Override
    public User update(User user) {
        User origin = repo.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + user.getId()));

        // 필요한 필드만 업데이트
        origin.setName(user.getName());
        //origin.setEmail(user.getEmail());
        origin.setRoles(user.getRoles());
        origin.setEnabled(user.getEnabled());

        // 비번 변경이 필요한 경우(옵션) — 이미 해시된 값으로 세팅해야 함
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            origin.setPassword(user.getPassword()); // 해시된 값으로 넣으세요
        }
        return origin; // 더티체킹으로 자동 update
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> list() {
        return repo.findAll();
    }
}
