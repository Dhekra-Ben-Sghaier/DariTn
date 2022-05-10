import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { AnnonceComponent } from 'src/app/annonce/annonce.component';
import { Annonce } from '../model/annonce';
import { AnnonceService } from './annonce-service';

describe('AnnonceService', () => {
  let component: AnnonceComponent;
  beforeEach(async ()=>{
    TestBed.configureTestingModule({
imports:[
  HttpClientModule,
],
declarations: [AnnonceComponent]
    })
    .compileComponents();
  })
  beforeEach(() => {
    const fixture = TestBed.createComponent(AnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
});
