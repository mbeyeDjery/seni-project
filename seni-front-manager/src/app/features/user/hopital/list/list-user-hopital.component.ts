import {Component, Input} from '@angular/core';
import {IHopital} from "../../../../core/model/hopital-model";

@Component({
  selector: 'app-list-user-hopital',
  standalone: true,
  imports: [],
  templateUrl: './list-user-hopital.component.html',
  styles: ``
})
export class ListUserHopitalComponent {
  @Input({required: true}) hopital: IHopital;
}
