package br.com.alunoonline.api.service;

import br.com.alunoonline.api.enums.MatriculadoStatusAlunoEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    // É aqui que o aluno vai se matricular

    public void criarMatricula(MatriculaAluno matriculaAluno){
        matriculaAluno.setStatus(MatriculadoStatusAlunoEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(long matricularAlunoId){
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matricularAlunoId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula aluno nao encontrada"));

        if (!MatriculadoStatusAlunoEnum.MATRICULADO.equals(matriculaAluno.getStatus())){
            // Larçar o erro se o status nao for matriculado
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível trançar uma matricula com o status Matriculado!");
        }

        matriculaAluno.setStatus(MatriculadoStatusAlunoEnum.TRANCADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void atualizaNotas(){

    }
}
