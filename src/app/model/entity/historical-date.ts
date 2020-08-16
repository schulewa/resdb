export class HistoricalDate {
  day: number;
  month: number;
  year: number;

    public toString(): string {
        return 'HistoricalDate: ' +
            ' day=' + this.day + ' month=' + this.month + ' year=' + this.year;
    }
}
