package org.patatesmaison.msreservation.mapper;

public interface EntityDTOMapper<E, D> {
    /**
     * Genere un DTO à partir d'une entité
     *
     * @param entity entité
     * @return dto
     */
    D fromEntity(E entity);

    /**
     * Enrichi un dto existant depuis une entité
     *
     * @param dto    dto existant
     * @param entity entité
     * @return dto
     */
    D fromEntity(D dto, E entity);

    /**
     * Génére une entité depuis un dto
     *
     * @param dto dto
     * @return entité
     */
    E fromDto(D dto);

    /**
     * Enrichie une entité depuis un dto
     *
     * @param entity entité existante
     * @param dto    dto
     * @return entité
     */
    E fromDto(E entity, D dto);
}
