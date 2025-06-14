package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserListResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class H2UserLoaderService {

    private static final Logger logger = LoggerFactory.getLogger(H2UserLoaderService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    public void loadUsersToH2() {
        try {
            ResponseEntity<UserListResponse> response = restTemplate.getForEntity(
                    "https://dummyjson.com/users", UserListResponse.class);

            if (response.getBody() == null || response.getBody().getUsers() == null) {
                logger.warn("No users received from API");
                return;
            }

            List<UserDTO> dtoList = response.getBody().getUsers();

            List<User> users = dtoList.stream().map(dto -> {
                try {
                    User user = new User();
                    user.setId(dto.getId());
                    user.setFirstName(dto.getFirstName());
                    user.setLastName(dto.getLastName());
                    user.setMaidenName(dto.getMaidenName());
                    user.setAge(dto.getAge());
                    user.setGender(dto.getGender());
                    user.setEmail(dto.getEmail());
                    user.setPhone(dto.getPhone());
                    user.setUsername(dto.getUsername());
                    user.setPassword(dto.getPassword());
                    user.setBirthDate(dto.getBirthDate());
                    user.setImage(dto.getImage());
                    user.setBloodGroup(dto.getBloodGroup());
                    user.setHeight(dto.getHeight());
                    user.setWeight(dto.getWeight());
                    user.setEyeColor(dto.getEyeColor());
                    user.setIp(dto.getIp());
                    user.setMacAddress(dto.getMacAddress());
                    user.setUniversity(dto.getUniversity());
                    user.setEin(dto.getEin());
                    user.setSsn(dto.getSsn());
                    user.setUserAgent(dto.getUserAgent());
                    user.setRole(dto.getRole());

                    if (dto.getHair() != null) {
                        Hair hair = new Hair();
                        hair.setColor(dto.getHair().getColor());
                        hair.setType(dto.getHair().getType());
                        user.setHair(hair);
                    }

                    if (dto.getAddress() != null) {
                        Address address = new Address();
                        address.setAddress(dto.getAddress().getAddress());
                        address.setCity(dto.getAddress().getCity());
                        address.setState(dto.getAddress().getState());
                        address.setStateCode(dto.getAddress().getStateCode());
                        address.setPostalCode(dto.getAddress().getPostalCode());
                        address.setCountry(dto.getAddress().getCountry());

                        if (dto.getAddress().getCoordinates() != null) {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setLat(dto.getAddress().getCoordinates().getLat());
                            coordinates.setLng(dto.getAddress().getCoordinates().getLng());
                            address.setCoordinates(coordinates);
                        }

                        user.setAddress(address);
                    }

                    if (dto.getBank() != null) {
                        Bank bank = new Bank();
                        bank.setCardExpire(dto.getBank().getCardExpire());
                        bank.setCardNumber(dto.getBank().getCardNumber());
                        bank.setCardType(dto.getBank().getCardType());
                        bank.setCurrency(dto.getBank().getCurrency());
                        bank.setIban(dto.getBank().getIban());
                        user.setBank(bank);
                    }

                    if (dto.getCompany() != null) {
                        Company company = new Company();
                        company.setDepartment(dto.getCompany().getDepartment());
                        company.setName(dto.getCompany().getName());
                        company.setTitle(dto.getCompany().getTitle());

                        if (dto.getCompany().getAddress() != null) {
                            Address compAddr = new Address();
                            compAddr.setAddress(dto.getCompany().getAddress().getAddress());
                            compAddr.setCity(dto.getCompany().getAddress().getCity());
                            compAddr.setState(dto.getCompany().getAddress().getState());
                            compAddr.setStateCode(dto.getCompany().getAddress().getStateCode());
                            compAddr.setPostalCode(dto.getCompany().getAddress().getPostalCode());
                            compAddr.setCountry(dto.getCompany().getAddress().getCountry());

                            if (dto.getCompany().getAddress().getCoordinates() != null) {
                                Coordinates compCoords = new Coordinates();
                                compCoords.setLat(dto.getCompany().getAddress().getCoordinates().getLat());
                                compCoords.setLng(dto.getCompany().getAddress().getCoordinates().getLng());
                                compAddr.setCoordinates(compCoords);
                            }

                            company.setAddress(compAddr);
                        }

                        user.setCompany(company);
                    }

                    if (dto.getCrypto() != null) {
                        Crypto crypto = new Crypto();
                        crypto.setCoin(dto.getCrypto().getCoin());
                        crypto.setWallet(dto.getCrypto().getWallet());
                        crypto.setNetwork(dto.getCrypto().getNetwork());
                        user.setCrypto(crypto);
                    }

                    return user;
                } catch (Exception e) {
                    logger.error("Failed to map user DTO with id: " + dto.getId(), e);
                    return null;
                }
            }).filter(Objects::nonNull).toList();

            userRepository.saveAll(users);
            logger.info("Loaded {} users into H2", users.size());

        } catch (RestClientException e) {
            logger.error("Error while fetching users from external API", e);
        } catch (Exception e) {
            logger.error("Unexpected error during user loading", e);
        }
    }
}
