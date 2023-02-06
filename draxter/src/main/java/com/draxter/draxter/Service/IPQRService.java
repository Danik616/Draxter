package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.PQR;

public interface IPQRService {
    public List<PQR> obtenerPQRs(String usuario);

    public List<PQR> obtenerPQRPorId(String id, String usuario);

    public PQR guardarPQR(PQR pqr);

    public List<PQR> obtenerTodosLosPQRs();

    public List<PQR> obtenerPQRPorIdAdmin(String id);

    public PQR obtenerUnPQRPorID(String id);

    public void eliminarPQR(String id);

}
