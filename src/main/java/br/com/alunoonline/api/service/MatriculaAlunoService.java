package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.api.dtos.DisciplinasAlunosReponse;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.api.enums.MatriculadoStatusAlunoEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {

    public static final double MEDIA_PARA_APROVACAO = 7.0;
    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    // É aqui que o aluno vai se matricular

    public void criarMatricula(MatriculaAluno matriculaAluno){
        matriculaAluno.setStatus(MatriculadoStatusAlunoEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(long matriculaAlunoId){
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula aluno nao encontrada"));

        if (!MatriculadoStatusAlunoEnum.MATRICULADO.equals(matriculaAluno.getStatus())){
            // Larçar o erro se o status nao for matriculado
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível trançar uma matricula com o status Matriculado!");
        }

        matriculaAluno.setStatus(MatriculadoStatusAlunoEnum.TRANCADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void atualizaNotas(Long matriculaAlunoId, AtualizarNotasRequest atualizarNotasRequest){
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula aluno nao encontrada"));

        // verifica se o front tá mandando a nota1
        // atualizarNotasRequest/getNota1(): Traz a notas que vem do front

        if (atualizarNotasRequest.getNota1() != null){

            // matriculaAluno.setNota1: atualiza a nota1 que vem atualmente do BD
            matriculaAluno.setNota1(atualizarNotasRequest.getNota1());
        }

        // verifica se o front tá mandando a nota2
        // atualizarNotasRequest/getNota2(): Traz a notas que vem do front

        if (atualizarNotasRequest.getNota2() != null){

            // matriculaAluno.setNota2: atualiza a nota2 que vem atualmente do BD
            matriculaAluno.setNota2(atualizarNotasRequest.getNota2());
        }

        calculaMedia(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    private void calculaMedia (MatriculaAluno matriculaAluno){
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if (nota1 != null && nota2 != null){
            Double media = (nota1 + nota2) / 2;

            matriculaAluno.setStatus(media >= MEDIA_PARA_APROVACAO ? MatriculadoStatusAlunoEnum.APROVADO : MatriculadoStatusAlunoEnum.REPROVADO);
        }
    }

    public HistoricoAlunoResponse emitirHistorico(Long alunoId){
        List<MatriculaAluno> matriculasDoAluno = matriculaAlunoRepository.findByAlunoId(alunoId);

        if (matriculasDoAluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Essse aluno não possui matriculas");
        }

        HistoricoAlunoResponse historicoAluno = new HistoricoAlunoResponse();
        historicoAluno.setNomeAluno(matriculasDoAluno.get(0).getAluno().getNome());
        historicoAluno.setCpfAluno(matriculasDoAluno.get(0).getAluno().getCpf());
        historicoAluno.setEmailAluno(matriculasDoAluno.get(0).getAluno().getEmail());

        List<DisciplinasAlunosReponse> disciplinasList = new ArrayList<>();

        for (MatriculaAluno matriculaAluno : matriculasDoAluno){
            DisciplinasAlunosReponse disciplinasAlunosReponse = new DisciplinasAlunosReponse();
            disciplinasAlunosReponse.setNomeDisciplina(matriculaAluno.getDisciplina().getNome());
            disciplinasAlunosReponse.setNomeProfessor(matriculaAluno.getDisciplina().getProfessor().getNome());
            disciplinasAlunosReponse.setNota1(matriculaAluno.getNota1());
            disciplinasAlunosReponse.setNota2(matriculaAluno.getNota2());

            // não quero isso nesse metodo, mas eu (prof) vou fazer

            if (matriculaAluno.getNota1() != null && matriculaAluno.getNota2() != null){
                disciplinasAlunosReponse.setMedia((matriculaAluno.getNota1() + matriculaAluno.getNota2()) / 2.0);
            } else {
                disciplinasAlunosReponse.setMedia(null);
            }

            disciplinasList.add(disciplinasAlunosReponse);
        }

        historicoAluno.setDisciplinasAlunosReponses(disciplinasList);

        return historicoAluno;
    }
}
