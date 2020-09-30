package com.order.demo.serviceImpl;


import com.order.demo.model.ContactUs;
import com.order.demo.model.Item;
import com.order.demo.repository.ContactUsRepository;
import com.order.demo.service.ContactUsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactUsServiceImpl implements ContactUsService{

    ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public ContactUs getContactUs(Long id) {
        Optional<ContactUs> contactUsService = contactUsRepository.findById(id);
        return contactUsService.get();

    }

    @Override
    public ContactUs getContactUs(String email) {
        return null;
    }

    @Override
    public ContactUs createContact(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);
    }

    @Override
    public boolean deleteContactUsById(Long id) {
        ContactUs contactUs = contactUsRepository.findById(id).orElse(null);
        if (contactUs == null) return false;
        contactUsRepository.delete(contactUs);
        return true;
    }

    @Override
    public List<ContactUs> getContactus() {
        return contactUsRepository.findAll();
    }
}
