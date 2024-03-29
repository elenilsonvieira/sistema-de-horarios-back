package br.edu.ifpb.dac.sistemadehorarios.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.security.spec.InvalidParameterSpecException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class SuapService {

	private static final String OBTAIN_TOKEN_URL = "https://suap.ifpb.edu.br/api/jwt/obtain_token/";
	private static final String PROFESSOR_URL = "https://suap.ifpb.edu.br/api/recursos-humanos/servidores/v1/?limit=100&offset=";
	private static final String COURSES_URL = "https://suap.ifpb.edu.br/api/ensino/cursos/v1/?limit=439&offset=1";
	private static final String CURRICULAR_COMPONENT_URL = "https://suap.ifpb.edu.br/api/ensino/matrizes/v1/?page=";
	private static final String EMPLOYEES_URL = "https://suap.ifpb.edu.br/api/recursos-humanos/servidores/v1/";
	private static final String STUDENTS_URL = "https://suap.ifpb.edu.br/api/ensino/alunos/v1/";

	private static final String USERNAME_JSON_FIELD = "username";
	private static final String PASSWORD_JSON_FIELD = "password";

	private static final String TOKEN_HEADER_NAME = "authorization";
	private static final String TOKEN_HEADER_VALUE = "Bearer %s";

	private static final Map<String, String> DEFAULT_HEADERS = Map.of("Content-Type", "application/json");

	private String suapToken;

	@Autowired
	private Gson gson;

	public String login(String username, String password) {
		Map<String, String> body = Map.of(USERNAME_JSON_FIELD, username, PASSWORD_JSON_FIELD, password);
		String json = gson.toJson(body);
		try{
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(OBTAIN_TOKEN_URL))
					.header("Content-Type", "application/json")
					.method("POST", HttpRequest.BodyPublishers.ofString(json))
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			this.suapToken = response.body().split(":")[2].substring(1).split("\"")[0];
			return suapToken;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public String findEmployee(String token, String username) {
		String url = String.format("%s?search=%s", EMPLOYEES_URL, username);
		return find(token, url);
	}

	public String findStudent(String token, String username) {
		String url = String.format("%s?search=%s", STUDENTS_URL, username);
		return find(token, url);
	}

	public String findAllCourses() {
		return find(suapToken, COURSES_URL);
	}

	public String findAllCurricularComponent() {
		return find(suapToken, CURRICULAR_COMPONENT_URL);
	}

	public JsonObject findUser(String token, String username) {
		String json = findEmployee(token, username);

		JsonArray result = JsonParser.parseString(json).getAsJsonObject()
				.get("results")
				.getAsJsonArray();

		if (result.size() == 0) {
			json = findStudent(token, username);
			result = JsonParser.parseString(json).getAsJsonObject()
					.get("results")
					.getAsJsonArray();
		}

		return result.size() == 0 ? null : result.get(0).getAsJsonObject();
	}

	private HttpRequest generateGetUrl(String url, Map<String, String> headers) throws URISyntaxException {
		Builder builder = HttpRequest.newBuilder().uri(new URI(url));

		for (Map.Entry<String, String> header : DEFAULT_HEADERS.entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}

		for (Map.Entry<String, String> header : headers.entrySet()) {
			builder.setHeader(header.getKey(), header.getValue());
		}

		return builder.GET().build();
	}

	private HttpRequest generatePostUrl(String url, Map<String, String> headers, String body)
			throws URISyntaxException {
		Builder builder = HttpRequest.newBuilder().uri(new URI(url));

		if (DEFAULT_HEADERS != null) {
			for (Map.Entry<String, String> header : DEFAULT_HEADERS.entrySet()) {
				builder.setHeader(header.getKey(), header.getValue());
			}
		}

		if (headers != null) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				builder.setHeader(header.getKey(), header.getValue());
			}
		}

		return builder.POST(BodyPublishers.ofString(body)).build();
	}

	private String sendRequest(HttpRequest httpRequest) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
	}

	private String find(String token, String findUrl) {
		try {
			HttpRequest url = generateGetUrl(findUrl,
					Map.of(TOKEN_HEADER_NAME, String.format(TOKEN_HEADER_VALUE, token)));
			return sendRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e3) {
			Thread.currentThread().interrupt();
			e3.printStackTrace();
		}

		return null;
	}

	public String findCurricular(int page) {
		try {
			HttpRequest url = generateGetUrl(CURRICULAR_COMPONENT_URL + page,
					Map.of(TOKEN_HEADER_NAME, String.format(TOKEN_HEADER_VALUE, suapToken)));
			return sendRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e3) {
			Thread.currentThread().interrupt();
			e3.printStackTrace();
		}

		return null;
	}

	public String findProfessors(int set) {
		try {
			HttpRequest url = generateGetUrl(PROFESSOR_URL + set,
					Map.of(TOKEN_HEADER_NAME, String.format(TOKEN_HEADER_VALUE, suapToken)));
			return sendRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e3) {
			Thread.currentThread().interrupt();
			e3.printStackTrace();
		}

		return null;
	}

	public String findAllCurriculumMatrix(String token) throws InvalidParameterSpecException {return null;}

	public String findOneCurriculumMatrix(String token, String id) throws InvalidParameterSpecException{return null;}

	public int findCurriculumMatrixWeekCH(String token, String id) throws InvalidParameterSpecException{return 0;}

	public String getSuapToken() {
		return suapToken;
	}

	public void setSuapToken(String suapToken) {
		this.suapToken = suapToken;
	}
}
