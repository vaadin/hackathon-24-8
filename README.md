# hackathon-24-8

### Feedback

Tried to eat own dogfood, i18n on an existing Hilla app.
- impossible to compile without patching Hilla: https://github.com/vaadin/hilla/issues/3553
- need a way to deal with dynamic keys: similar to https://github.com/vaadin/hilla/issues/3478
- will need to make sure that keys from ViewConfig go to main chunk (part of 3553)

Played with `FormLayout` in the same application
- struggled to get column span working: https://github.com/vaadin/docs/issues/4360
- some concerns about default look of a form

In my opinion, for a good DX journey, if I write:

```tsx
<FormLayout>
  <TextField/>
  <TextField/>
  <Button/>
</FormLayout>
```

it should already look nice. I see two small text fields, too small in my opinion, doesn't look like a layout. I expect something more than just stacking the components, otherwise I have `VerticalLayout` for that.
