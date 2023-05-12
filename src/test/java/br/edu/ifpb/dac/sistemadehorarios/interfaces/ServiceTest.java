package br.edu.ifpb.dac.sistemadehorarios.interfaces;

import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;

public interface ServiceTest {
    void attributesAreNotNull();
    void testCreateNewEntity() throws CurricularComponentInvalidException;
    void testReadEntities();
    void testFindOneEntityById();
    void testUpdateOneEntityById();
    void testDeleteOneEntityById();
}
