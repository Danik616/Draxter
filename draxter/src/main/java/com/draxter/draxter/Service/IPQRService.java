package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.PQR;

public interface IPQRService {
    public List<PQR> obtenerPQRs(String usuario);

    public PQR obtenerPQRPorId(Long id, String usuario);

    public PQR guardarPQR(PQR pqr);

    public List<PQR> obtenerTodosLosPQRs();
}
