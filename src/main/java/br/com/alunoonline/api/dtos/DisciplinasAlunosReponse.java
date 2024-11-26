package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.MatriculadoStatusAlunoEnum;
import lombok.Data;

@Data
public class DisciplinasAlunosReponse {
    private String nomeDisciplina;
    private String nomeProfessor;
    private Double nota1;
    private Double nota2;
    private Double media;
    private MatriculadoStatusAlunoEnum status;;
}
