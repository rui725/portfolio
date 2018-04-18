import { Component, Inject } from '@angular/core';
//ModelDriven import
import { FormGroup, FormControl, Validator, Validators, FormBuilder } from '@angular/forms';
// Service
import { MediaItemService} from './media.item.service';
import {lookupListToken} from './providers';

import {Router } from '@angular/router';


@Component({
  selector: 'mw-media-item-form',
  templateUrl: 'app/media-item-form.component.html',
  styleUrls: ['app/media-item-form.component.css']
})
export class MediaItemFormComponent {
  //modeldriven
  form;

  //Services Class constructor Injection
  constructor(private formBuilder: FormBuilder,
              private mediaItemService: MediaItemService,
              @Inject(lookupListToken) public lookupLists,
              private router: Router){

  }

  ngOnInit(){
    this.form = this.formBuilder.group({
      medium: this.formBuilder.control('Movies'),
      // Built-In Validators
      name: this.formBuilder.control('',Validators.compose([
          Validators.required,
          Validators.pattern('[\\w\\-\\s\\/]+')
      ]
      )),
      category:this.formBuilder.control,
      year: this.formBuilder.control('', this.yearValidator)
    });
  }

  //Model-Driven
  /*
  ngOnInit(){
    this.form = new FormGroup({
      medium: new FormControl('Movies'),
      // Built-In Validators
      name: new FormControl('',Validators.compose([
          Validators.required,
          Validators.pattern('[\\w\\-\\s\\/]+')
      ]
      )),
      category: new FormControl(''),
      year: new FormControl('', this.yearValidator)
    });
  }
  */
  // custom Validator
  yearValidator(control){
    if(control.value.trim().length === 0){
      return null;
    }
    let year = parseInt(control.value);
    let minYear = 1900;
    let maxYear = 2100;
    if(year >= minYear && year <= maxYear){
      return null;
    }else{
      return { 'year': {
        'min': minYear,
        'max': maxYear
      }
    };
    }
  }

  //template driven and model driven
  onSubmit(mediaItem){
    this.mediaItemService.add(mediaItem).subscribe(() => {
      this.router.navigate(['/', mediaItem.medium])
    });
  }
}
