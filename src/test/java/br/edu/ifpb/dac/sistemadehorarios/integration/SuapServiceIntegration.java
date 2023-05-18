package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.service.SuapService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.spec.InvalidParameterSpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuapServiceIntegration {

    @Mock
    private Mock mock;

    @Mock
    @Autowired
    private static SuapService suapService;
    @BeforeAll
    public static void setUp() {}

    @Test
    @Order(1)
    @DisplayName("verify FindAllCurriculumMatrix is executed have")
    public void verifyFindAllCurriculumMatrixIsInvoked(){
        try {
            suapService.findAllCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA");
            verify(suapService).findAllCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA");
        }catch (InvalidParameterSpecException e){}
    }

    @Test
    @Order(2)
    @DisplayName("verify if FindAllCurriculumMatrix have return")
    public void findAllCurriculumMatrixHaveReturn(){
        try {
            when(suapService.findAllCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA"))
                    .thenReturn("{\"url\":\"https://suap.ifpb.edu.br/api/ensino/matrizes/v1/d32d99db-9bce-48b6-bd00-955f81771701/\",\"uuid\":\"d32d99db-9bce-48b6-bd00-955f81771701\",\"descricao\":\"Tecnologia em Análise e Desenvolvimento de Sistemas (2021/1) - Monteiro\",\"ano_criacao\":2021,\"periodo_criacao\":1,\"componentes\":[{\"uuid\":\"a3e4771d-d282-474b-81b8-839b3545bf0f\",\"descricao\":\"Algoritmos e Lógica de Programação\",\"periodo_letivo\":1,\"ch_pratica\":0,\"ch_presencial\":67},{\"uuid\":\"e80363bc-647a-43ee-addd-7499fb6b0506\",\"descricao\":\"Inglês Instrumental I\",\"periodo_letivo\":1,\"ch_pratica\":0,\"ch_presencial\":33}]}");

            assertEquals(
                    "{\"url\":\"https://suap.ifpb.edu.br/api/ensino/matrizes/v1/d32d99db-9bce-48b6-bd00-955f81771701/\",\"uuid\":\"d32d99db-9bce-48b6-bd00-955f81771701\",\"descricao\":\"Tecnologia em Análise e Desenvolvimento de Sistemas (2021/1) - Monteiro\",\"ano_criacao\":2021,\"periodo_criacao\":1,\"componentes\":[{\"uuid\":\"a3e4771d-d282-474b-81b8-839b3545bf0f\",\"descricao\":\"Algoritmos e Lógica de Programação\",\"periodo_letivo\":1,\"ch_pratica\":0,\"ch_presencial\":67},{\"uuid\":\"e80363bc-647a-43ee-addd-7499fb6b0506\",\"descricao\":\"Inglês Instrumental I\",\"periodo_letivo\":1,\"ch_pratica\":0,\"ch_presencial\":33}]}",
                    suapService.findAllCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA"));
        }catch (InvalidParameterSpecException e){}
    }

    @Test
    @Order(3)
    @DisplayName("verify if FindAllCurriculumMatrix have Throw Exception")
    public void findAllCurriculumMatrixHaveThrow(){
        try {

            when(suapService.findAllCurriculumMatrix("Invalid.TOKEN")).thenThrow(new InvalidParameterSpecException());
            assertThrows(InvalidParameterSpecException.class, () -> suapService.findAllCurriculumMatrix("Invalid.TOKEN"));

        }catch (InvalidParameterSpecException e){}
    }

    @Test
    @Order(4)
    @DisplayName("verify if FindOneCurriculumMatrix is executed have")
    public void findOneCurriculumMatrixIsInvoked(){
        try {
            suapService.findOneCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                                                    "a3e4771d-d282-474b-81b8-839b3545bf0f");

            verify(suapService).findOneCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                    "a3e4771d-d282-474b-81b8-839b3545bf0f");

        }catch (InvalidParameterSpecException e){}
    }


    @Test
    @Order(5)
    @DisplayName("verify if FindOneCurriculumMatrix have return")
    public void findOneCurriculumMatrixHaveReturn(){
        try {
            when(suapService.findOneCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                    "a3e4771d-d282-474b-81b8-839b3545bf0f"))
                    .thenReturn("{\"uuid\":\"a3e4771d-d282-474b-81b8-839b3545bf0f\",\"descricao\":\"Algoritmos e Lógica de Programação\",\"periodo_letivo\":1,\"ch_pratica\":0,\"ch_presencial\":67}");


            assertEquals(
                    "{\"uuid\":\"a3e4771d-d282-474b-81b8-839b3545bf0f\",\"descricao\":\"Algoritmos e Lógica de Programação\",\"periodo_letivo\":1,\"ch_pratica\":0,\"ch_presencial\":67}",
                    suapService.findOneCurriculumMatrix("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                            "a3e4771d-d282-474b-81b8-839b3545bf0f"));

        }catch (InvalidParameterSpecException e){}
    }

    @Test
    @Order(6)
    @DisplayName("verify if FindOneCurriculumMatrix have Thow Exception")
    public void findOneCurriculumMatrixHaveThrow(){
        try {
            when(suapService.findOneCurriculumMatrix("Invalid.TOKEN", "Invalid.ID")).thenThrow(new InvalidParameterSpecException());
            assertThrows(InvalidParameterSpecException.class, () -> suapService.findOneCurriculumMatrix("Invalid.TOKEN", "Invalid.ID"));

        }catch (InvalidParameterSpecException e){}
    }


    @Test
    @Order(7)
    @DisplayName("verify if findCurriculumMatrixWeekCH is executed have")
    public void findCurriculumMatrixWeekCHIsInvoked(){
        try {
            suapService.findCurriculumMatrixWeekCH("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                    "a3e4771d-d282-474b-81b8-839b3545bf0f");

            verify(suapService).findCurriculumMatrixWeekCH("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                    "a3e4771d-d282-474b-81b8-839b3545bf0f");

        }catch (InvalidParameterSpecException e){}
    }


    @Test
    @Order(8)
    @DisplayName("verify if findCurriculumMatrixWeekCH have return")
    public void findCurriculumMatrixWeekCHHaveReturn(){
        try {
            when(suapService.findCurriculumMatrixWeekCH("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                    "a3e4771d-d282-474b-81b8-839b3545bf0f"))
                    .thenReturn(33);


            assertEquals(33,
                    suapService.findCurriculumMatrixWeekCH("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDI5Nzc1LCJpYXQiOjE2ODQ0MjYxNzUsImp0aSI6IjYwYjZkMmU5NmI3YTQ2NzA5MmZkOWQwNDljYTg2NzcwIiwidXNlcl9pZCI6MTM2MDk4fQ.g3xuNIbEnvJ1V90ANbjdfFGgyMu1yjGOva3CMQRh_kA",
                            "a3e4771d-d282-474b-81b8-839b3545bf0f"));

        }catch (InvalidParameterSpecException e){}
    }

    @Test
    @Order(9)
    @DisplayName("verify if findCurriculumMatrixWeekCH have Thow Exception")
    public void findCurriculumMatrixWeekCHHaveThrow(){
        try {
            when(suapService.findCurriculumMatrixWeekCH("Invalid.TOKEN", "Invalid.ID")).thenThrow(new InvalidParameterSpecException());
            assertThrows(InvalidParameterSpecException.class, () -> suapService.findCurriculumMatrixWeekCH("Invalid.TOKEN", "Invalid.ID"));

        }catch (InvalidParameterSpecException e){}
    }

}
