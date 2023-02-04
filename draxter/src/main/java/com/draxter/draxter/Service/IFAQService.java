package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.FAQ;

public interface IFAQService {

    public FAQ guardarFAQ(FAQ faq);

    public List<FAQ> obtenerFAQs();
}
