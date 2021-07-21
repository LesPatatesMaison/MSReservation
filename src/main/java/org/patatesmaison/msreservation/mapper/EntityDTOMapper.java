package org.patatesmaison.msreservation.mapper;

import org.patatesmaison.msreservation.exception.APIException;

public interface EntityDTOMapper<E, D> {
    /**
     * Genere un DTO à partir d'une entité
     *
     * @param entity entité
     * @return dto
     */
    D fromEntity(E entity) throws APIException;

    /**
     * Enrichi un dto existant depuis une entité
     *
     * @param dto    dto existant
     * @param entity entité
     * @return dto
     */
    D fromEntity(D dto, E entity) throws APIException;

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
