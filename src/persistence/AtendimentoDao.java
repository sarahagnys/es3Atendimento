import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Cliente;

import model.Atendimento;

public class AtendimentoDao implements IObjDao<Atendimento> {
	private SessionFactory sf;

	public AtendimentoDaoDao(SessionFactory sf) {
		this.sf = sf;
	}
	@Override
	public void insere(Atendimento atend) {
		// TODO Auto-generated method stub
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(atend);
		transaction.commit();
	}

	@Override
	public void selectOne(Atendimento atend) {
		// TODO Auto-generated method stub
		EntityManager entityManager = sf.createEntityManager();
		atend = entityManager.find(Atendimento.class, atend.getCliente());
		return atend;
	}

	@Override
	public void selectOneCliente(Atendimento atend) {
		// TODO Auto-generated method stub
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT *");
		buffer.append("FROM atendimento atend, ");
		buffer.append("INNER JOIN cliente cli on atend.cpf_cliente = cli.cpf_cliente ");
		buffer.append("INNER JOIN atendente at on atend.email = atendente.email,");
		buffer.append("WHERE id_cliente = '?' and id_atendente = '?' and data = '?'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Atendimento atend = new Atendimento();
			atend.setCliente(obj[0].toString());
			atend.setAtendente(obj[1].toString());
			atend.setDataHora(obj[2].toString());

			atendimentos.add(atend);
		}

		return atendimentos;
		
	}

	@Override
	public selectOneAtendente(Atendimento atend) {
		// TODO Auto-generated method stub
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT *");
		buffer.append("FROM cliente cli");
		buffer.append("INNER JOIN atendente at on cli.cpf_cliente = at.cpf_cliente ");
		buffer.append("INNER JOIN atendimento atend on atend.email = cli.email,");
		buffer.append("WHERE id_cliente = '?'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Atendimento atend = new Atendimento();
			atend.setCpf(obj[0].toString());
			atend.setNome(obj[1].toString());
			atend.setEmail(obj[2].toString());
			atend.setCelular(obj[3].toString());
			atend.setPronomeTratamento(obj[4].toString());

			atendimentos.add(atend);
		}

		return atendimentos;
	}

	@Override
	public selectAll<Atendimento> lista() {
		// TODO Auto-generated method stub
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT *");
		buffer.append("FROM atendente at");
		buffer.append("INNER JOIN funcionario func at on func.cpf_cliente = at.cpf_cliente ");
		buffer.append("INNER JOIN atendimento atend on atend.email = ate.email,");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Atendimento atend = new Atendimento();
			atend.setHoraEntrada(obj[0].toString());
			atend.setHoraSaida(obj[1].toString());
			atend.setEmail(obj[2].toString());

			atendimentos.add(atend);
		}

		return atendimentos;
	}
	@Override
	public List<Atendimento> lista() {
		// TODO Auto-generated method stub
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT *");
		buffer.append("FROM atendente at");
		buffer.append("INNER JOIN funcionario func at on func.cpf_cliente = at.cpf_cliente ");
		buffer.append("INNER JOIN atendimento atend on atend.email = ate.email,");
		buffer.append("WHERE id_cliente = '?'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Atendimento atend = new Atendimento();
			atend.setCliente(obj[0].toString());
			atend.setAtendente(obj[1].toString());
			atend.setDataHora(obj[2].toString());

			atendimentos.add(atend);
		}

		return atendimentos;
	}
}
