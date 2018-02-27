export class Deal {
  id: number;
  content: String;
  completed: boolean;

  constructor(id: number, content: String, completed: boolean) {
    this.id = id;
    this.content = content;
    this.completed = completed;
  }
}
