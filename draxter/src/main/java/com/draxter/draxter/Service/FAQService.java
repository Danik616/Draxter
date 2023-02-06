package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.FAQ;
import com.draxter.draxter.Repository.IFAQRepository;

@Service
public class FAQService implements IFAQService {

    private IFAQRepository faqRepository;

    public FAQService(IFAQRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public FAQ guardarFAQ(FAQ faq) {
        return faqRepository.save(faq);
    }

    @Override
    public void eliminarFAQsporID(String id) {
        if (id != null) {
            long idBusqueda = Long.parseLong(id);
            faqRepository.deleteById(idBusqueda);
        }
    }

    @Override
    public FAQ obtenerFAQporID(String id) {
        long idBusqueda = Long.parseLong(id);
        return faqRepository.obtenerFaqPorID(idBusqueda);
    }

    @Override
    public List<FAQ> obtenerFAQs() {
        return faqRepository.findAll();
    }

}
