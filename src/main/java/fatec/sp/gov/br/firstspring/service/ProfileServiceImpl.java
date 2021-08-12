package fatec.sp.gov.br.firstspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.sp.gov.br.firstspring.entity.Login;
import fatec.sp.gov.br.firstspring.entity.Profile;
import fatec.sp.gov.br.firstspring.repository.LoginRepository;
import fatec.sp.gov.br.firstspring.repository.ProfileRepository;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Profile> getAll() {
        List<Profile> profiles = new ArrayList<Profile>();
        for(Profile profile: profileRepository.findAll()){
            profiles.add(profile);
        }
        return profiles;
    }

    @Override
    public Profile getProfileById(long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isPresent()){
            return profile.get();
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public Profile postProfile(Profile profile) {

        Login login = loginRepository.getById(profile.getLogin().getId());

        if(login == null){
            throw new RuntimeException("Login não encontrado");
        }else{

            profile.setLogin(login);
            profile.setName(profile.getName());
            profile.setDoc(profile.getDoc());
            profile.setGender(profile.getGender());
            profile.setBirthday(profile.getBirthday());
            profile.setPhone(profile.getPhone());
            profile.setMobile(profile.getMobile());
            profile.setCreated_at(profile.getCreated_at());

            profileRepository.save(profile);

            Profile newProfile = profileRepository.getById(profile.getId());

            return newProfile;
            
        }

    }

    @Override
    public void deleteProfileById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile putProfile(Profile profile) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
