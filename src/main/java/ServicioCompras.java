public class ServicioCompras {

    private static final double MONTO_LIMITE = 1000.0;

    private final ProveedorService proveedorService;
    private final AprobacionService aprobacionService;

    public ServicioCompras(ProveedorService proveedorService,
                           AprobacionService aprobacionService) {
        this.proveedorService = proveedorService;
        this.aprobacionService = aprobacionService;
    }

    public String registrarCompra(String proveedorId, String ordenId,
                                  double monto, boolean aprobado,
                                  String fechaEntrega) {

        if (!proveedorEstaActivo(proveedorId))
            return "El proveedor no esta activo";

        if (requiereAprobacion(monto) && !tieneAprobacion(ordenId))
            return "La compra requiere aprobacion";

        return "Compra registrada correctamente";
    }

    private boolean proveedorEstaActivo(String proveedorId) {
        return proveedorService.estaActivo(proveedorId);
    }

    private boolean requiereAprobacion(double monto) {
        return monto > MONTO_LIMITE;
    }

    private boolean tieneAprobacion(String ordenId) {
        return aprobacionService.tieneAprobacion(ordenId);
    }
}