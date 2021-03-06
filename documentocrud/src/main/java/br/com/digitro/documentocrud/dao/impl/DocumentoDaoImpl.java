package br.com.digitro.documentocrud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import br.com.digiro.documentocrud.factory.ConnectionFactory;
import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.model.Documento;

public class DocumentoDaoImpl implements DocumentoDao{
	
	public List getTodosDocumentos() {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM documentos_timestamp;");
			List<Documento> documentos = new ArrayList<Documento>();
			
			while(rs.next()) {
				Documento documento = extrairDocumentoDeResultSet(rs);
				documentos.add(documento);
			}
			rs.close();
			stmt.close();
			return documentos;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro. Banco está vazio.");
		}
				
		//return null;
	}

	public Documento getDocumentoPorId(int id){
		Connection connection = ConnectionFactory.getConnection();
		Documento documento = new Documento();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM documentos_timestamp WHERE id=" + id);
			if(rs.next()){
 				documento = extrairDocumentoDeResultSet(rs);
				rs.close();
				stmt.close();
				return documento;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Documentos não encontrados.");
		}
		documento = null;
		return documento;
	}
	
	public List<Documento> getDocumentosPorFiltroTituloTexto(Documento documento){
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM documentos_timestamp WHERE 1 = 1");
			if(documento.getId() != 0) {
				sql.append("AND id=").append(documento.getId());
			}
			if(documento.getTitulo() != null) {
				sql.append("AND UPPER(titulo) LIKE '%").append(documento.getTitulo().toUpperCase()).append("%'");
			}
			if (documento.getTexto() != null) {
				sql.append("AND UPPER(corpo) LIKE '%").append(documento.getTexto().toUpperCase()).append("%'");
			}
			ResultSet rs = stmt.executeQuery(sql.toString());
			List<Documento> documentos = new ArrayList<Documento>();

			while(rs.next()) {
				documento = extrairDocumentoDeResultSet(rs);
				documentos.add(documento);
			}
			rs.close();
			stmt.close();
			return documentos;

		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Documentos não encontrados.");
		}
		
	}

	public List<Documento> getDocumentosPorData(Documento documento) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM documentos_timestamp WHERE 1 = 1");
			if (documento.getDataCriacao() != null) {
				sql.append("AND datacriacao >= '%").append(documento.getDataCriacao().toString()).append("%'");
			}else if (documento.getDataFim() != null) {
				sql.append("AND datacriacao <= '%").append(documento.getDataFim().toString()).append("%'");
			}else {
				sql.append("AND datacriacao BETWEEN '%").append(documento.getDataCriacao().toString()).
				append("%'").append("AND '%").append(documento.getDataFim().toString()).append("%'");
			}
			//ResultSet rs = stmt.executeQuery("SELECT * FROM documentos_timestamp;");
			ResultSet rs = stmt.executeQuery(sql.toString());
			List<Documento> documentos = new ArrayList<Documento>();

			while(rs.next()) {
				documento = extrairDocumentoDeResultSet(rs);
				documentos.add(documento);
			}
			rs.close();
			stmt.close();
			return documentos;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Documentos não encontrados.");
		}
	}
	
	public List<Documento> getDocumentosPorIntervaloData(Documento documento) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM documentos_timestamp WHERE 1 = 1");
			if (documento.getDataCriacao() != null && documento.getDataFim() != null) {
				sql.append("AND datacriacao BETWEEN '%").append(documento.getDataCriacao().toString()).
				append("%'").append("AND '%").append(documento.getDataFim().toString()).append("%'");
			}
			ResultSet rs = stmt.executeQuery(sql.toString());
			List<Documento> documentos = new ArrayList<Documento>();

			while(rs.next()) {
				documento = extrairDocumentoDeResultSet(rs);
				documentos.add(documento);
			}
			rs.close();
			stmt.close();
			return documentos;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Documentos não encontrados.");
		}
	}
	
	public double insertDocumento(Documento documento) {
		Connection connection  = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO documentos_timestamp(titulo, corpo) VALUES (?, ?)");
			//ps.setInt(1, documento.getId());
			ps.setString(1, documento.getTitulo());
			ps.setString(2, documento.getTexto());
			int i  = ps.executeUpdate();
			if(i == 1) {
				ps.close();
				return 1d; 
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			//throw new RuntimeException("Erro. Documento Não inserido");
		}
		return 0d;
	}
	
	public double updateDocumento(Documento documento) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE documentos_timestamp SET titulo=?,corpo=? where id=?");
			ps.setInt(3, documento.getId());
			ps.setString(1, documento.getTitulo());
			ps.setString(2, documento.getTexto());
			int i = ps.executeUpdate();
			if(i == 1) {
				return 1d;
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0d;
	}
	
	public double deleteDocumento(int id) {
		Connection connection  = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM documentos_timestamp WHERE id=" + id);
			if(i == 1) {
				return 1d;
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0d;
	}
	
	@Override
	public boolean limparBanco() {
		Connection connection  = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("TRUNCATE TABLE documentos_timestamp RESTART IDENTITY;");
			if(i == 1) {
				return true;
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean zerarId() {
		Connection connection  = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("ALTER SEQUENCE documento_timestamp_seq RESTART WITH 1");
			if(i == 1) {
				return true;
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean criaBanco() {
		Connection connection  = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("CREATE TABLE public.documentos_timestamp\n" + 
					"(\n" + 
					"  id integer NOT NULL DEFAULT nextval('documento_timestamp_seq'::regclass),\n" + 
					"  titulo character varying(250),\n" + 
					"  corpo text,\n" + 
					"  datacriacao timestamp with time zone NOT NULL DEFAULT now(),\n" + 
					"  CONSTRAINT documentos_timestamp_pkey PRIMARY KEY (id)\n" + 
					")\n" + 
					"WITH (\n" + 
					"  OIDS=FALSE\n" + 
					");" + 
					"ALTER TABLE public.documentos_timestamp\n" + 
					"  OWNER TO digitro;\n" + 
					"GRANT ALL ON TABLE public.documentos_timestamp TO digitro;\n" + 
					"GRANT ALL ON TABLE public.documentos_timestamp TO digitro;");
			if(i == 1) {
				return true;
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Documento extrairDocumentoDeResultSet(ResultSet rs) throws SQLException {
 		Documento documento = new Documento();
		documento.setId(rs.getInt("id"));
		documento.setTitulo(rs.getString("titulo"));
		documento.setTexto(rs.getString("corpo"));
		Timestamp dataInicio = rs.getTimestamp("datacriacao");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		documento.setDataCriacao(dateFormat.format(dataInicio));
		return documento;
	}

	
}
