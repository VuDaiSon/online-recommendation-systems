package com.example.recommendershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userGroups")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userGroupId;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "userGroups",cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    private Set<User> users;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "usergroup_rolegroup",
            joinColumns = @JoinColumn(name = "usergroup_id"),
            inverseJoinColumns = @JoinColumn(name = "rolegroup_id")
    )    private Set<RoleGroup> roleGroups;
}
