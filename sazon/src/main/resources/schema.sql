-- =============================================
-- TABLA: Categoria
-- =============================================
CREATE TABLE IF NOT EXISTS Categoria (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(200),
    Condicion BOOLEAN
    );

-- =============================================
-- TABLA: Platillo
-- =============================================
CREATE TABLE IF NOT EXISTS Platillo (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(200),
    Categoria INT NOT NULL,
    Precio DECIMAL(10,2),
    Condicion BOOLEAN,
    CONSTRAINT fk_categoria FOREIGN KEY (Categoria) REFERENCES Categoria(Id)
    );

-- =============================================
-- TABLA: Venta
-- =============================================
CREATE TABLE IF NOT EXISTS Venta (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Fecha TIMESTAMP,
    MetodoPago VARCHAR(50) NOT NULL,
    Tipo VARCHAR(20) NULL,
    Estado VARCHAR(20) NOT NULL,

    -- Nuevos campos
    NombreCliente VARCHAR(150),
    DireccionCliente VARCHAR(250),
    TelefonoCliente VARCHAR(50),
    Referencia VARCHAR(200)
    );

-- =============================================
-- TABLA: DetalleVenta
-- =============================================
CREATE TABLE IF NOT EXISTS DetalleVenta (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Venta INT NOT NULL,
    Platillo INT NOT NULL,
    Cantidad INT NOT NULL,
    CONSTRAINT fk_venta FOREIGN KEY (Venta) REFERENCES Venta(Id),
    CONSTRAINT fk_platillo FOREIGN KEY (Platillo) REFERENCES Platillo(Id)
);
