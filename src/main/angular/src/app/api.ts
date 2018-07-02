export abstract class Api {
  static HOST = 'http://localhost:8080/api';
  static LOGIN = Api.HOST + '/login';
  static REGISTER = Api.HOST + '/register';
  static TASKS = Api.HOST + '/tasks';
}
