package com.test.ds.entites;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntityBase {
    @Id
    private UUID id = UUID.randomUUID();
    private String createdBy;
    private String modifiedBy;
    private LocalTime createdDate;
    private LocalTime modifiedDate;

    @PrePersist
    public void setCreatedDate(){
        this.createdDate = LocalTime.now();
    }
    @PreUpdate
    public void setModifiedDate() {
        this.modifiedDate = LocalTime.now();
    }
}
