import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { FileComponent } from 'src/app/file/file.component';

describe('FileService', () => {
  let component: FileComponent;
  beforeEach(async ()=>{
    TestBed.configureTestingModule({
imports:[
  HttpClientModule,
],
declarations: [FileComponent]
    })
    .compileComponents();
  })
  beforeEach(() => {
    const fixture = TestBed.createComponent(FileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
});
