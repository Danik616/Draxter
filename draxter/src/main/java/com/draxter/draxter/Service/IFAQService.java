package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.FAQ;

public interface IFAQService {

    public FAQ guardarFAQ(FAQ faq);

    public void eliminarFAQsporID(String id);

    public FAQ obtenerFAQporID(String id);

    public List<FAQ> obtenerFAQs();
}
