package application.charityboxmanager.api.dto;

import application.charityboxmanager.model.CollectionBox;

public record CollectionBoxDto(Long id, boolean empty, boolean assigned) {

    public static CollectionBoxDto fromEntity(CollectionBox collectionBox){
        return new CollectionBoxDto(collectionBox.getId(), collectionBox.isEmpty(), collectionBox.isAssigned());
    }

}
