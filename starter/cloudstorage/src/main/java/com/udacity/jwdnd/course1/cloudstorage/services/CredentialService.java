package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    private final UserService userService;

    public CredentialService(
            CredentialMapper credentialMapper,
            EncryptionService encryptionService,
            UserService userService
    ) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public List<CredentialDTO> getCredentialListByUserId() {
        List<Credential> credentialList = credentialMapper.getAllByUserId(userService.getUserId());

        return credentialList.stream()
                .map(
                        credential -> new CredentialDTO(
                            credential.getCredentialId(),
                            credential.getUrl(),
                            credential.getUsername(),
                            credential.getKey(),
                            credential.getPassword(),
                            encryptionService.decryptValue(
                                    credential.getPassword(),
                                    credential.getKey()
                            )
                        )
                )
                .collect(Collectors.toList());
    }

    public void createCredential(Credential credential) {
        credential.setUserId(userService.getUserId());
        credential.setKey(generateKey());

        credential.setPassword(
                encryptionService.encryptValue(
                        credential.getPassword(),
                        credential.getKey()
                )
        );

        credentialMapper.insert(credential);
    }

    public void updateCredential(Credential credential) {
        credential.setUserId(userService.getUserId());
        credential.setKey(generateKey());

        credential.setPassword(
                encryptionService.encryptValue(
                        credential.getPassword(),
                        credential.getKey()
                )
        );

        credentialMapper.update(credential);
    }

    public boolean deleteCredential(Integer credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public String generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

}
