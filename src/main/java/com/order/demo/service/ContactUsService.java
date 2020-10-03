package com.order.demo.service;


import com.order.demo.model.ContactUs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactUsService{

    ContactUs getContactUs(Long id);

    ContactUs getContactUs(String email);

    ContactUs createContact(ContactUs contactUs);

    List<ContactUs> getContactus();

    boolean deleteContactUsById(Long id);
}
