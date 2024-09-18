package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId( Long id){
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id){
        alunoRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno){

        // Primeiro: Ver se o aluno existe no BD
        Optional<Aluno> alunoDoBancoDeDados = buscarAlunoPorId(id);
        // E se não existir?
        if (alunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado no banco de dados!");
        }
            //Se chegar aqui, significa que existe aluno, então vou armazena-lo em uma variavel

        Aluno alunoEditado = alunoDoBancoDeDados.get();

        // Com esse aluno editado de cima, faço...
        // faço os sets necessarios para atualizar os atributos deles.

        alunoEditado.setNome(aluno.getNome());
        alunoEditado.setCpf(aluno.getCpf());
        alunoEditado.setEmail(aluno.getEmail());

        //Com o aluno totalmente editado acima
        //eu devolvo ele atualizado para o banco de dados

        alunoRepository.save(alunoEditado);

    }


}


