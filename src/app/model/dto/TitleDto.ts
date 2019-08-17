export class TitleDto {
  id: number;
  title: string;
  description: string;
  titleType: string;
  appliesTo: string;
  status: string;
  createdBy: string;
  updatedBy: string;
  lastUpdated: Date;
  selected: boolean;
  action: string;
  isDataChanged: boolean;
}
