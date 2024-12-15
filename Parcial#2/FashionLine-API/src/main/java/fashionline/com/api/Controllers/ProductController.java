package fashionline.com.api.Controllers;

import fashionline.com.api.Models.DAO.Service.SProduct;
import fashionline.com.api.Models.DAO.Service.SProductI;
import fashionline.com.api.Models.DTO.ErrorDTO;
import fashionline.com.api.Models.DTO.ProductDTO;
import fashionline.com.api.Models.Entity.Category;
import fashionline.com.api.Models.Entity.Size;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductController {
    private final SProductI serviceProductI;

    @Autowired
    public ProductController(SProduct serviceProductI) {
        this.serviceProductI = serviceProductI;
    }


    @GetMapping("/All")
    @Operation(
            summary = "Obtener todos los productos",
            description = "Recupera una lista de todos los productos disponibles.",
            responses = {
                    @ApiResponse(
                            description = "Productos encontrados exitosamente",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(
                            description = "Productos no encontrados",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ProductDTO.class)
                                    ))
                    )
            }
    )
    public ResponseEntity<?> getAllProducts() {
        try {
            return new ResponseEntity<>(serviceProductI.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/All")
    @Operation(
            summary = "Obtener todas las categorías de productos",
            description = "Recupera todas las categorías disponibles para productos en la plataforma.",
            responses = {
                    @ApiResponse(
                            description = "Categorías encontradas",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Category.class,
                                                    description = "Lista de categorías disponibles")
                                    ))
                    ),
                    @ApiResponse(
                            description = "Error al recuperar las categorías",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getAllCategorys() {
        try {
            return new ResponseEntity<>(List.of(Category.values()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/size/All")
    @Operation(
            summary = "Obtener todas los tamaños de productos",
            description = "Recupera todas los tamaños disponibles para productos en la plataforma.",
            responses = {
                    @ApiResponse(
                            description = "Tamaños encontrados",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Category.class,
                                                    description = "Lista de categorías disponibles")
                                    ))
                    ),
                    @ApiResponse(
                            description = "Error al recuperar los tamaños",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getAllSizes() {
        try {
            return new ResponseEntity<>(List.of(Size.values()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener producto por ID",
            description = "Recupera un producto específico basado en su ID.",
            responses = {
                    @ApiResponse(
                            description = "Producto encontrado",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(
                            description = "Producto no encontrado",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(serviceProductI.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{name}")
    @Operation(
            summary = "Buscar productos por nombre",
            description = "Recupera una lista de productos basados en su nombre.",
            responses = {
                    @ApiResponse(
                            description = "Productos encontrados",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ProductDTO.class)
                                    ))
                    ),
                    @ApiResponse(
                            description = "Productos no encontrados",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> getProductsByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(serviceProductI.getProductsByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/category/{category}")
    @Operation(
            summary = "Buscar productos por categoría",
            description = "Recupera una lista de productos basados en su categoría.",
            responses = {
                    @ApiResponse(
                            description = "Productos encontrados",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ProductDTO.class)
                                    ))
                    ),
                    @ApiResponse(
                            description = "Productos no encontrados",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        try {
            return new ResponseEntity<>(serviceProductI.getProductsByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/Create")
    @Operation(
            summary = "Crear un nuevo producto",
            description = "Crea un nuevo producto en la base de datos.",
            responses = {
                    @ApiResponse(
                            description = "Producto creado exitosamente",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(
                            description = "Productos no encontrados",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            return new ResponseEntity<>(serviceProductI.createProduct(productDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/Update/{id}")
    @Operation(
            summary = "Actualizar un producto existente",
            description = "Actualiza un producto existente usando el ID y la nueva información proporcionada.",
            responses = {
                    @ApiResponse(
                            description = "Producto actualizado exitosamente",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(
                            description = "Producto no actualizado",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        try {
            return new ResponseEntity<>(serviceProductI.updateProduct(id, productDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/Delete/{id}")
    @Operation(
            summary = "Eliminar un producto por ID",
            description = "Elimina un producto de la base de datos usando su ID.",
            responses = {
                    @ApiResponse(
                            description = "Producto eliminado exitosamente",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(
                            description = "Producto no eliminado",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            return new ResponseEntity<>(serviceProductI.deleteProduct(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
