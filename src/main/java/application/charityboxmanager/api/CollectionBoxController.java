package application.charityboxmanager.api;

import application.charityboxmanager.api.dto.CollectionBoxDto;
import application.charityboxmanager.api.dto.StoredMoneyDto;
import application.charityboxmanager.service.CollectionBoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/box")
public class CollectionBoxController {

    private final CollectionBoxService collectionBoxService;

    public CollectionBoxController(CollectionBoxService collectionBoxService) {
        this.collectionBoxService = collectionBoxService;
    }

    @GetMapping
    public ResponseEntity<List<CollectionBoxDto>> getAllBoxes(){
        return ResponseEntity.ok(collectionBoxService.getAllCollectionBox());
    }
    @PostMapping("/{boxId}/money")
    public ResponseEntity<Void> addMoneyToBox(@PathVariable Long boxId,@RequestBody StoredMoneyDto dto) {
        collectionBoxService.addMoneyToBox(boxId, dto.amount(), dto.currency());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    public ResponseEntity<CollectionBoxDto> registerNewBox() {
        CollectionBoxDto createdBox = collectionBoxService.registerCollectionBox();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBox);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> removeBox(@PathVariable long id) {
        collectionBoxService.removeCollectionBox(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
