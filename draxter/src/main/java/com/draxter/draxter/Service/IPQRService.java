package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.PQR;

public interface IPQRService {
    public List<PQR> obtenerPQRs();

    public PQR obtenerPQRPorId(Long id);

    public PQR guardarPQR(PQR pqr);
}
