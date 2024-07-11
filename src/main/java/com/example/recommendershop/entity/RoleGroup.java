package com.example.recommendershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roleGroups")
public class RoleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleGroupId;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roleGroups",cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private Set<UserGroup> userGroups;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "rolegroup_role",
            joinColumns = @JoinColumn(name = "rolegroup_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )     private Set<Role> roles = new HashSet<>();
}
