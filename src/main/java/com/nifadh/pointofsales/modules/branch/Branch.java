package com.nifadh.pointofsales.modules.branch;

import com.nifadh.pointofsales.modules.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "manager_id")
    private User manager;
}
