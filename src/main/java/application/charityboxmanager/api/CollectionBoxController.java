package application.charityboxmanager.api;

import application.charityboxmanager.api.dto.CollectionBoxDto;
import application.charityboxmanager.api.dto.StoredMoneyDto;
import application.charityboxmanager.service.CollectionBoxService;
import application.charityboxmanager.service.MoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/box")
public class CollectionBoxController {

    private final CollectionBoxService collectionBoxService;
    private final MoneyService moneyService;

    public CollectionBoxController(CollectionBoxService collectionBoxService, MoneyService moneyService) {
        this.collectionBoxService = collectionBoxService;
        this.moneyService = moneyService;
    }

    @GetMapping
    public ResponseEntity<List<CollectionBoxDto>> getAllBoxes(){
        return ResponseEntity.ok(collectionBoxService.getAllCollectionBox());
    }
    @PostMapping("/{boxId}/money")
    public ResponseEntity<Void> addMoneyToBox(@PathVariable Long boxId,@RequestBody StoredMoneyDto dto) {
        moneyService.addMoneyToBox(boxId, dto.amount(), dto.currency());
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
