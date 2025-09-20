# Sistema de Gestión de Inventario - Java

## Descripción
Proyecto estudiantil desarrollado durante la **segunda semana del módulo de Java en Riwi**. Sistema de gestión de inventario con interfaz gráfica que permite administrar productos, realizar compras y generar facturas.

## Funcionalidades

### 📦 Gestión de Productos
- **Agregar productos**: Soporte para dos tipos de productos:
  - **Food**: Productos alimenticios con fecha de vencimiento
  - **Appliance**: Electrodomésticos
- **Listar inventario**: Visualización completa de productos con stock
- **Buscar producto**: Búsqueda por nombre (no sensible a mayúsculas/minúsculas)
- **Ordenar por precio**: Visualización de productos ordenados de menor a mayor precio

### 🛒 Sistema de Compras
- **Comprar productos**: Selector dropdown con precios y stock visible
- **Carrito de compras**: Gestión de productos seleccionados
- **Control de stock**: Validación automática de disponibilidad

### 🧾 Facturación
- **Checkout**: Proceso de compra completo
- **Factura profesional**: Generación de factura con:
  - Fecha y hora
  - Detalle de productos
  - Subtotal, IVA (19%) y total
  - Formato profesional
- **Actualización de inventario**: Reducción automática de stock

### ✅ Validaciones
- Validación de datos de entrada (nombres, precios, cantidades, fechas)
- Control de duplicados con opción de agregar stock
- Verificación de stock disponible

## Arquitectura del Proyecto

```
src/
├── app/
│   └── Main.java                 # Punto de entrada
├── controller/
│   └── MenuController.java       # Control de interfaz y flujo
├── model/
│   ├── Product.java             # Clase abstracta base
│   ├── Food.java                # Productos alimenticios
│   └── Appliance.java           # Electrodomésticos
├── interfaces/
│   └── ProductService.java      # Contrato de servicios
├── services/
│   └── ProductServiceImpl.java  # Implementación de lógica de negocio
└── helper/
    ├── Inventory.java           # Gestión de inventario
    ├── CartHelper.java          # Gestión del carrito
    └── InputValidator.java      # Validaciones de entrada
```

## Tecnologías Utilizadas
- **Java 8+**
- **Swing** (Interfaz gráfica)
- **Collections Framework** (ArrayList, HashMap)
- **LocalDate/LocalTime** (Manejo de fechas)

## Cómo Ejecutar

### Requisitos
- Java JDK 8 o superior
- IDE (recomendado: IntelliJ IDEA, Eclipse, o VS Code)

### Pasos para ejecutar

1. **Clonar/Descargar el proyecto**
   ```bash
   git clone https://github.com/osvaldocs/entrenamientoSem2Java
   cd entrenamientoSem2Java
   ```

2. **Compilar el proyecto**
   ```bash
   javac -d out src/app/*.java src/controller/*.java src/helper/*.java src/interfaces/*.java src/model/*.java src/services/*.java
   ```

3. **Ejecutar la aplicación**
   ```bash
   java -cp out app.Main
   ```

### Desde IDE
1. Importar el proyecto en tu IDE
2. Configurar el directorio `src` como Source Root
3. Ejecutar la clase `Main.java`

## Datos de Prueba
El sistema incluye productos de prueba al iniciar:
- **Apple** - $3,500 (25 unidades)
- **Milk** - $4,200 (18 unidades)  
- **Television** - $1,850,000 (8 unidades)

## Uso del Sistema
1. Al ejecutar aparece un menú con 7 opciones
2. Selecciona la opción deseada usando los botones
3. Sigue las instrucciones en pantalla
4. Para comprar: usa la opción "Buy product" y luego "Check Out"

## Autor
**Pablo Campos** - Estudiante Riwi  
*Semana 2 - Módulo Java*

---
*Proyecto educativo desarrollado como parte del programa de formación en Riwi*
